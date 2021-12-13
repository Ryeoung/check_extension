/*
 * 탬플릿을 파싱해서 값을 집어 넣는 객체
 */
class TemplateParser {
	/*
	 * 바인딩할 데이터가 객체 하나일 경우 템플릿 작업을 통해 resultHTML을 반환
	 */
	getResultHTML(template, data){
		let bindTemplate = Handlebars.compile(template);
		let resultHTML = bindTemplate(data);
		return resultHTML.trim();
	}
	
	/*
	 * string data -> HTML DOM
	 */
	stringToElement(str){
    	return  document.createRange().createContextualFragment(str);
    }
}
const templateParser = new TemplateParser();
export default templateParser;