package com.flow.extension.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.extension.domain.Extension;
import com.flow.extension.service.ExtensionService;
import com.flow.extension.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExtensionController.class)
public class ExtensionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExtensionService extensionService;

    /**
     * 모든 파일 확장자 데이터 가져오기
     */
    @Test
    void getExtensions() throws Exception {
        List<Extension> list = TestUtil.getExtensionList();
        given(extensionService.findAll()).willReturn(list);

        ResultActions actions = mvc.perform(get("/api/extensions")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", is("success")))
                .andDo(print());


    }

    /**
     * 파일 확장자 삽입 체크
     */
    @Test
    void addExtension() throws Exception{
        Extension e = TestUtil.getExtension();
        e.setExtensionId(20L);
        e.setName("ece");
        System.out.println(e);
        given(extensionService.insertExtension(any())).willReturn(e);

        ObjectMapper objectMapper = new ObjectMapper();
        String inserted = objectMapper.writeValueAsString(e);
        ResultActions actions = mvc.perform(post("/api/extension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inserted))
                .andDo(print());



        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", is("success")))
                .andDo(print());
    }

    /**
     * 파일 확장자 수정 체크
     */
    @Test
    void updateFromExtension() throws Exception{
        Extension e = TestUtil.getExtension();

        e.setName("ece");
        System.out.println(e);

        given(extensionService.updateExtension(anyLong(),any())).willReturn(e);

        ObjectMapper objectMapper = new ObjectMapper();
        String inserted = objectMapper.writeValueAsString(e);
        ResultActions actions = mvc.perform(put("/api/extension/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inserted))
                .andDo(print());



        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", is("success")))
                .andDo(print());
    }

    /**
     * 파일 확장자 삭제 테스트
     */
    @Test
    void deleteFromExtension() throws Exception{
        Extension e = TestUtil.getExtension();

        e.setName("ece");
        System.out.println(e);

        given(extensionService.deleteExtension(anyLong())).willReturn(true);

        ResultActions actions = mvc.perform(delete("/api/extension/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());



        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", is("success")))
                .andDo(print());
    }

}
