package com.flow.extension.controller;

import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import com.flow.extension.enums.ResponseStatus;
import com.flow.extension.exceptions.ExtensionDuplicateException;
import com.flow.extension.exceptions.ExtensionNotFoundException;
import com.flow.extension.exceptions.MaxDataOfCustomExtensionException;
import com.flow.extension.response.ResponseMessage;
import com.flow.extension.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/api")
public class ExtensionController {
    private final ExtensionService extensionService;

    @Autowired
    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    /**
     * @return ResponseEntity<ResponseMessage> 성공 반환 데이터
     *   DB에 저장된 모든 파일 확장자 데이터 가져오기
     */
    @GetMapping("/extensions")
    public ResponseEntity<ResponseMessage> getExtensions() {
        List<Extension> extensionList = extensionService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(extensionList));
    }


    /**
     * @param extension DB에 삽입할 파일 확장자
     * @return ResponseEntity<ResponseMessage> 성공 반환 데이터
     *
     *  매개변수로 받는 파일확장자 객체를 DB에 삽입한다.
     */
    @PostMapping("/extension")
    public ResponseEntity<ResponseMessage> addExtension(@Valid @RequestBody Extension extension) {
        int cnt = extensionService.countByExtensionType(ExtensionType.CUSTOM);
        if(cnt >= 200) {
            throw new MaxDataOfCustomExtensionException(ResponseStatus.MAX_DATA_OF_CUSTOM_ERROR.getMsg());
        }
        Extension inserted = extensionService.insertExtension(extension);

        if(inserted == null) {
            throw new ExtensionDuplicateException(ResponseStatus.EXTENSION_EXISIT.getMsg());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(inserted));
    }

    /**
     * @param extension 업데이트할 파일 확장자 객체
     * @return ResponseEntity<ResponseMessage> 성공 반환 데이터
     *  매개변수로 받은 파일 확장자 객체를 DB에서
     */
    @PutMapping("/extension/{extensionId}")
    public ResponseEntity<ResponseMessage> updateFromExtension(@PathVariable Long extensionId, @RequestBody Extension extension) {
        Extension updated = extensionService.updateExtension(extensionId, extension);
        if(updated == null ){
            throw new ExtensionNotFoundException(ResponseStatus.EXTENSION_NOT_FOUND.getMsg());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    /**
     * @param extensionId 삭제할 파일 확장자 아이디
     * @return ResponseEntity<ResponseMessage> 성공 반환 데이터
     */
    @DeleteMapping("/extension/{extensionId}")
    public ResponseEntity<ResponseMessage> deleteFromExtension(@PathVariable Long extensionId) {
        boolean isDelete = extensionService.deleteExtension(extensionId);
        if(!isDelete) {
            throw new ExtensionNotFoundException(ResponseStatus.EXTENSION_NOT_FOUND.getMsg());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }
}
