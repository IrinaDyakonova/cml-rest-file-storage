package cml.rest.file.storage.test;

import cml.rest.file.storage.test.model.File;
import cml.rest.file.storage.test.rest.RestEndPoints;
import cml.rest.file.storage.test.service.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestEndPoints.class)
public class FileStorageIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileService service;

    private File mockFile;


    @Before
    public void setMockFile() {
        mockFile = new File();
        mockFile.setId("9u9j9HMBbLppMj1NaAwT");
        mockFile.setName("text.txt");
        mockFile.setSize(2);
    }

    @Test
    public void attemptFileCreateWithoutParams() throws Exception {
        mvc.perform(post("/file").content("{}").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.errors", hasSize(2)));
    }

    @Test
    public void attemptFileCreateWithoutName() throws Exception {
        mvc.perform(post("/file").content("{\"size\":123456}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void attemptFileCreateWithEmptyName() throws Exception {
        mvc.perform(post("/file").content("{\"name\":\"\",\"size\":123456}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void attemptFileCreateWithInvalidNameFormat() throws Exception {
        mvc.perform(post("/file").content("{\"name\":\"test\",\"size\":123456}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void attemptFileCreateWithoutSize() throws Exception {
        mvc.perform(post("/file").content("{\"name\":\"test.txt\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void attemptFileCreateWithoutInvalidSize() throws Exception {
        mvc.perform(post("/file").content("{\"name\":\"test.txt\",\"size\":-2}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void attemptFileCreateWithValidParams() throws Exception {
        Mockito.when(service.saveFile(Mockito.any())).thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/file")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test.txt\", \"size\":2}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
