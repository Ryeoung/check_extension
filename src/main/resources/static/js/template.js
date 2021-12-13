const template = {
    checkBoxTemplate: `{{#each .}}
                            {{#if block}}
                                <input type="checkbox" name="fixed_extension" value="{{extensionId}}" checked /> {{name}}
                            {{else}}
                                <input type="checkbox" name="fixed_extension" value="{{extensionId}}" /> {{name}}
                            {{/if}}
                        {{/each}}`,

    customExtensionListTemplate: `{{#each .}}
                                    <div class="custom_extension_tag flex_box flex_dir_row align_center_center" data-id={{extensionId}}>{{name}}
                                    <input type="button" class = "delete_btn" value="X"></div>

                                {{/each}}`,
    customExtensionTemplate: `      <div class="custom_extension_tag flex_box flex_dir_row align_center_center" data-id={{extensionId}}>{{name}}
                                    <input type="button" class = "delete_btn" value="X"></div>`

}
Object.freeze(template);

export default template;