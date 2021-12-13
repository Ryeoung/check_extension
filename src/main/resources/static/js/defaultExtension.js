import template from "./template.js";

import templateParser from "./templateParser.js";

export class DefaultExtension{
    constructor(defaultExtensions, ajax){
        this.ajax = ajax;
        this.checkBoxArticle = document.getElementById('checkBoxArticle');
        this.makeCheckBox(defaultExtensions);
        this.addChangeEvent();
    }
    /*
     * 
     * @param  extensions 고정 확장자 데이터(DB에서 받아온 데이터)
     * 
     * 고정확장자 데이터를 checkbox tag로 바꾸어 html에 붙인다.
     */
    makeCheckBox(extensions) {
        let checkboxHTML = templateParser.getResultHTML(template.checkBoxTemplate, extensions);
        let checkboxs = templateParser.stringToElement(checkboxHTML);
        this.checkBoxArticle.appendChild(checkboxs);
    }
    /*
     * checkbox event를 걸어준다.
     */
    addChangeEvent() {
        Array.from(this.checkBoxArticle.children).forEach((checkbox) => {
            checkbox.addEventListener("click", (event) =>{
                let checkboxElmt = event.currentTarget;
                this.requestChangeExtensionBlock(checkboxElmt);
            });
        });
    }

    /*
     *
     * @param checkboxElmt 체크박스 요소
     *
     * 체크박스에 토글 될 때 데이터 변경 요청 (Ajax)
     */
    requestChangeExtensionBlock(checkboxElmt) {
        let data = {};
        if(checkboxElmt.checked) {
            data["block"] = true;
        } else {
            data["block"] = false;
        }
        let extensionId = checkboxElmt.value;
        this.ajax({
            url : `/flow/api/extension/${extensionId}`,
            method : "PUT",
            contentType : "application/json; charset=utf-8",
            data: data
        }, ()=>{});
    }



}