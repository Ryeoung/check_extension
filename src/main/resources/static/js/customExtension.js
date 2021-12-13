import template from "./template.js";

import templateParser from "./templateParser.js";

export class CustomExtension{
    constructor(customExtension, ajax) {
        this.customExtensionTagCnt = 0;
        this.customExtensionCntElmt = document.getElementById("tag_cnt");
        this.customExtensionContainer = document.getElementById("customExtensionTagArticle");
        this.customExtensionInput = document.getElementById("custom_extension_input");
        this.customExtensionInputSubmit = document.getElementById("custom_extension_submit");
        this.makeCustomExtensionTags(customExtension);

        this.ajax = ajax;
        this.addKeyUpEvent();
        this.addSubitExtensionEvent()
        
    }
    /*
     * 커스텀 파일확장자에 공백이 들어가선 안되는 규칙 체크
     */
    addKeyUpEvent() {
        this.customExtensionInput.addEventListener("keyup", (event) => {
            let inputData = this.customExtensionInput.value;
            if(inputData.length > 20) {
                this.customExtensionInput.value = this.customExtensionInput.value.slice(0, -1);
                
            }

            //공백 제거
            this.customExtensionInput.value = this.customExtensionInput.value.replace(/ /g,"");
        });
    }
    /*
     * 커스텀 파일 확장자 추가
     */
    addSubitExtensionEvent() {
        this.customExtensionInputSubmit.addEventListener("click", (event) => {
            if(this.customExtensionTagCnt < 200) {
                this.requestPushExtensionToServer();
            }

            this.customExtensionInput.value = "";

        });
    }

    /*
     * 커스텀 확장자 등록 요청(ajax)
     */
    requestPushExtensionToServer() {
        let data = {
            "name" : this.customExtensionInput.value.trim(),
            "type" : "CUSTOM",
            "block" : true
        };

        this.ajax({
            url : `/flow/api/extension`,
            method : "POST",
            contentType : "application/json; charset=utf-8",
            data: data
        }, (responseMessage) => {
            this.makeCustomExtensionTag(responseMessage.data);
        });

    }

    /*
     *
     * @param customExtension 커스텀 확장자 데이터
     *
     * 커스텀 확장자 데이터로 커스텀 확장자 HTML 요소를 만든다.
     */
    makeCustomExtensionTag(customExtension) {
        let customExtensionTagHTML = templateParser.getResultHTML(template.customExtensionTemplate, customExtension);
        let customExtensionTag = templateParser.stringToElement(customExtensionTagHTML);
        this.addClickEventOfDeleteBtn(customExtensionTag);

        this.customExtensionContainer.appendChild(customExtensionTag);
        this.updateCustomExtensionCnt(1);

    }

    /*
     *
     * @param customExtensionList 커스텀 확장자 데이터 리스트
     *
     * 커스텀 확장자 데이터로 CustomExtension HTML Element로 변경
     */
    makeCustomExtensionTags(customExtensionList) {
        let customExtensionTagsHTML = templateParser.getResultHTML(template.customExtensionListTemplate, customExtensionList);
        let customExtensionTags = templateParser.stringToElement(customExtensionTagsHTML);
        Array.from(customExtensionTags.children).forEach((customExtensionElmt) =>{
            this.addClickEventOfDeleteBtn(customExtensionElmt);
        });

        this.customExtensionContainer.appendChild(customExtensionTags);
        this.updateCustomExtensionCnt(customExtensionList.length);
    }

    /*
     *
     * @param customExtensionElmt 커스텀 확장자 html 테그
     *
     * 커스텀 확장자 html 태그 안에 삭제 버튼 이벤트 등록
     */
    addClickEventOfDeleteBtn(customExtensionElmt) {
        let deleteBtn = customExtensionElmt.children[0];
        deleteBtn.addEventListener("click", (event) => {
            this.requestDeleteCustomExtension(customExtensionElmt);
        })

    }

    /*
     *
     * @param customExtensionElmt 커스텀 확장자 HTML 테그
     *
     * ajax로 커스텀 확장자 삭제 요청
     */
    requestDeleteCustomExtension(customExtensionElmt) {
        let customExtensionId = customExtensionElmt.dataset.id;

        this.ajax({
            url : `/flow/api/extension/${customExtensionId}`,
            method : "DELETE",
            contentType : "application/json; charset=utf-8"
        }, () => {
            this.customExtensionContainer.removeChild(customExtensionElmt);
            this.updateCustomExtensionCnt(-1);
        });
    }

    /*
     *
     * @param cnt 변경될 숫자
     *
     * 변경될 숫자만큼 커스텀 확장자 수를 변경한고 이를 HTML 요소에 반영
     */
    updateCustomExtensionCnt(cnt) {
        this.customExtensionTagCnt += cnt;
        this.customExtensionCntElmt.innerHTML = this.customExtensionTagCnt;
    }
}