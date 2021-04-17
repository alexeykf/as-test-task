package alexeykf.testtask;

import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@UtilityClass
public class TestUtils {

    public static MockHttpServletRequestBuilder asAdmin(MockHttpServletRequestBuilder builder) {
        return builder.with(user("admin").roles("ADMIN"));
    }
}
