package alexeykf.testtask.integration;

import alexeykf.testtask.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;
import java.sql.SQLException;

import static alexeykf.testtask.TestUtils.asAdmin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void setup(@Autowired DataSource ds) throws SQLException {
        try (var connect = ds.getConnection()) {
            ScriptUtils.executeSqlScript(connect, new ClassPathResource("sql/insert_customers.sql"));
        }
    }

    @Test
    public void testGetCustomer() throws Exception {
        mockMvc.perform(asAdmin(get("/customers/aa29c1b8-5b67-4dc1-ae4c-14c19bd460eb")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Is.is("aa29c1b8-5b67-4dc1-ae4c-14c19bd460eb")))
                .andExpect(jsonPath("$.title").value(Is.is("Kevin Cabrera")))
                .andExpect(jsonPath("$.createdAt").value(IsNot.not(Matchers.empty())))
                .andExpect(jsonPath("$.modifiedAt").value(Is.is(Matchers.nullValue())));
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(asAdmin(get("/customers")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(IsNot.not(Matchers.empty())));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        MvcResult result = mockMvc.perform(asAdmin(post("/customers")
                .content("{\"title\": \"New User\"}").contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(IsNot.not(Matchers.empty())))
                .andExpect(jsonPath("$.title").value(Is.is("New User")))
                .andExpect(jsonPath("$.createdAt").value(IsNot.not(Matchers.empty())))
                .andExpect(jsonPath("$.modifiedAt").value(Is.is(Matchers.nullValue())))
                .andReturn();
        JsonNode tree = objectMapper.readTree(result.getResponse().getContentAsString());
        String id = tree.get("id").asText();

        mockMvc.perform(asAdmin(get("/customers/" + id)))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateCustomerProduct() throws Exception {
        mockMvc.perform(asAdmin(post("/customers/f0a4ab9e-3023-4b29-bd74-c6f67001786d/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New product\", \"description\": \"Yet another product\", \"price\":\"10.20\"}")))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        mockMvc.perform(asAdmin(
                put("/customers/2abb3af2-4b8b-4016-a2ce-dcba3c98fb88")
                        .content("{\"title\": \"Updated customer\"}").contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Is.is("2abb3af2-4b8b-4016-a2ce-dcba3c98fb88")))
                .andExpect(jsonPath("$.title").value(Is.is("Updated customer")))
                .andExpect(jsonPath("$.createdAt").value(IsNot.not(Matchers.empty())))
                .andExpect(jsonPath("$.modifiedAt").value(IsNot.not(Matchers.empty())));
    }

    @Test
    public void testGetDeletedCustomer() throws Exception {
        mockMvc.perform(asAdmin(get("/customers/2f23e279-96f3-4df9-9d70-58182e675d51")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(asAdmin(delete("/customers/01b51fde-4e6b-49df-b011-f49bc59eac23")))
                .andExpect(status().isNoContent());

        mockMvc.perform(asAdmin(get("/customers/01b51fde-4e6b-49df-b011-f49bc59eac23")))
                .andExpect(status().isNotFound());
    }
}
