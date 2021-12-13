import ajax from "./ajax.js";
import {
    DefaultExtension
} from './defaultExtension.js';

import {
    CustomExtension
} from './customExtension.js';

class App {
    constructor() {
    	this.extensionsDict = {};
        this.requestAllExtensions();
        this.defaultExtension = null;
        this.customExtension = null;
    }
    /*
     * Ajax로 모든 확장자 객체를 받아온다.
     */
    requestAllExtensions(){
        ajax({
            url : "/flow/api/extensions",
            method : "GET",
            contentType : "application/json; charset=utf-8"
        }, this.getExtensionsSuccess.bind(this));
    }
    /*
     * 모든 확장자 객체 받아오기 성공했을 경우
     * extensionsDick에 담는다.
     * 다른 객체 초기화
     */
    getExtensionsSuccess(responseMessage) {
        this.extensionsDict['DEFAULT'] = [];
        this.extensionsDict['CUSTOM'] = [];
        
        responseMessage.data.forEach((extension) => {
            if(extension.type === 'DEFAULT' || extension.type === 'CUSTOM') {
                this.extensionsDict[extension.type].push(extension);
            }
        });

        this.defaultExtension = new DefaultExtension(this.extensionsDict["DEFAULT"], ajax);
        this.customExtension  = new CustomExtension(this.extensionsDict["CUSTOM"], ajax);

    }

}
window.onload = () => {
    new App();
}