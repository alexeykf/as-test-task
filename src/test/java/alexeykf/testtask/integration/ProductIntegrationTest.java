package alexeykf.testtask.integration;

import alexeykf.testtask.AbstractIntegrationTest;
import alexeykf.testtask.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.SQLException;

import static alexeykf.testtask.TestUtils.asAdmin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public void setup(@Autowired DataSource ds) throws SQLException {
        try (var connect = ds.getConnection()) {
            ScriptUtils.executeSqlScript(connect, new ClassPathResource("sql/insert_products.sql"));
        }
    }

    @Test
    public void testGetProducts() throws Exception {
        mockMvc.perform(asAdmin(get("/customers/b8fa0a16-bca9-4491-8b01-b7164250f539/products")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(Is.is(2)));
    }

    @Test
    public void testGetProductsWithDeletedProduct() throws Exception {
        mockMvc.perform(asAdmin(get("/customers/5fb25077-f1d1-4537-8da2-ad6e34db17af/products")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(Is.is(0)));
    }

    @Test
    public void testGetProduct() throws Exception {
        mockMvc.perform(asAdmin(get("/products/63e9799f-2333-419c-87c6-55c91dcb9450")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Is.is("63e9799f-2333-419c-87c6-55c91dcb9450")))
                .andExpect(jsonPath("$.title").value(Is.is("Second product")))
                .andExpect(jsonPath("$.description").value(Is.is("Description of Second product")))
                .andExpect(jsonPath("$.price").value(Is.is("120.50")));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(asAdmin(delete("/products/6352e702-086b-43fa-8a58-c0be80dce114")))
                .andExpect(status().isNoContent());

        mockMvc.perform(asAdmin(delete("/products/6352e702-086b-43fa-8a58-c0be80dce114")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetProductOfDeletedUser() throws Exception {
        mockMvc.perform(asAdmin(get("/products/f808f5c6-5289-422e-8db2-44d45071ad6e")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProductOfDeletedUser() throws Exception {
        mockMvc.perform(asAdmin(put("/products/f808f5c6-5289-422e-8db2-44d45071ad6e"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated product\", \"price\": \"123.33\", \"description\": \"Updated product\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateDeletedProduct() throws Exception {
        mockMvc.perform(asAdmin(put("/products/875fc52a-e1c3-4eb8-b816-faf45a8d96b8"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated product\", \"price\": \"123.33\", \"description\": \"Updated product\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mockMvc.perform(asAdmin(put("/products/5fb25077-f1d1-4537-8da2-ad6e34db17af"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated product\", \"price\": \"123.33\", \"description\": \"Updated product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Is.is("5fb25077-f1d1-4537-8da2-ad6e34db17af")))
                .andExpect(jsonPath("$.title").value(Is.is("Updated product")))
                .andExpect(jsonPath("$.description").value(Is.is("Updated product")))
                .andExpect(jsonPath("$.price").value(Is.is("123.33")));
    }

    @Test
    public void testGetDeletedProduct() throws Exception {
        mockMvc.perform(asAdmin(get("/products/875fc52a-e1c3-4eb8-b816-faf45a8d96b8")))
                .andExpect(status().isNotFound());
    }
}
