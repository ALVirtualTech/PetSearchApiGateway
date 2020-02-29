package ru.airlightvt.onlinerecognition.api_gateway.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;

/**
 * Тесты для REST контроллера для работы с объявлениями и поиском животных
 * @since 5.10.2018
 * @author apolyakov
 */
@RunWith(MockitoJUnitRunner.class)
public class RestServiceControllerTest {
    private InputStream is;
    private MockMvc mockMvc;

    @Spy
    @InjectMocks
    private RestServiceController controller = new RestServiceController();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        is = controller.getClass().getClassLoader().getResourceAsStream("test1_.jpg");
    }

    /**
     * Тестирование загрузки изображения питомца
     * @throws Exception
     */
    @Test
    public void testUploadPetPhoto() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("petPhoto", "test1_.jpg", "multipart/form-data", is);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/pets").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("Successfully uploaded - test1_.jpg", result.getResponse().getContentAsString());
    }
}
