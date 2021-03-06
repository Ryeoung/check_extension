package com.flow.extension.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import com.flow.extension.exceptions.ExtensionDuplicateException;
import com.flow.extension.exceptions.ExtensionNotFoundException;
import com.flow.extension.exceptions.MaxDataOfCustomExtensionException;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
     * 파일 확장자 삽입 체크 ( 삽입하고 싶은 데이터가 이미 있는 경우)
     */
    @Test
    void addExtensionDuplicateException() throws Exception{
        Extension e = TestUtil.getExtension();
        e.setExtensionId(20L);
        e.setName("ece");
        System.out.println(e);
        given(extensionService.countByExtensionType(ExtensionType.CUSTOM)).willReturn(1);
        given(extensionService.insertExtension(any())).willReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String inserted = objectMapper.writeValueAsString(e);
        ResultActions actions = mvc.perform(post("/api/extension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inserted))
                .andDo(print());



        actions.andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExtensionDuplicateException))
                .andDo(print());
    }

    /**
     * 파일 확장자 삽입 체크 ( 커스텀 확장자 데이터가 200개가 넘개 이미 있을 경우)
     */
    @Test
    void addExtensionMaxDataException() throws Exception{
        Extension e = TestUtil.getExtension();
        e.setExtensionId(20L);
        e.setName("ece");
        System.out.println(e);
        given(extensionService.countByExtensionType(ExtensionType.CUSTOM)).willReturn(200);
        given(extensionService.insertExtension(any())).willReturn(e);

        ObjectMapper objectMapper = new ObjectMapper();
        String inserted = objectMapper.writeValueAsString(e);
        ResultActions actions = mvc.perform(post("/api/extension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inserted))
                .andDo(print());



        actions.andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MaxDataOfCustomExtensionException))
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
     * 파일 확장자 수정 체크( 수정하고자 하는 데이터가 없을 경우)
     */
    @Test
    void updateFromExtensionNotFoundException() throws Exception{
        Extension e = TestUtil.getExtension();

        e.setName("ece");
        System.out.println(e);

        given(extensionService.updateExtension(anyLong(),any())).willReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String inserted = objectMapper.writeValueAsString(e);
        ResultActions actions = mvc.perform(put("/api/extension/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inserted))
                .andDo(print());



        actions.andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExtensionNotFoundException))
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
    /**
     * 파일 확장자 삭제 테스트( 삭제할 데이터가 없는 경우 )
     */
    @Test
    void deleteFromExtensionNotFoundException() throws Exception{
        Extension e = TestUtil.getExtension();

        e.setName("ece");
        System.out.println(e);

        given(extensionService.deleteExtension(anyLong())).willReturn(false);

        ResultActions actions = mvc.perform(delete("/api/extension/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());



        actions.andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExtensionNotFoundException))
                .andDo(print());
    }

}
