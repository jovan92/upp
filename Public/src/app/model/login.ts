export class Login {
    processInstanceId: String;
    taskId: String;
    formFields: [FormFields];
}

export class FormFields {
    businessKey: Boolean;
    defaultValue: any;
    id: String;
    label: String;
    properties: any;
    type: any;
    typeName: String;
    validationConstraints: any
    value: any;

    // function (value: any) {
    //     this.businessKey = value['businessKey'];
    //     this.defaultValue = value['defaultValue'];
    //     this.id = value['id'];
    //     this.label = value['label'];
    //     this.properties = value['properties'];
    //     this.type = value['type'];
    //     this.typeName = value['typeName'];
    //     this.validationConstraints = value['validationConstraints'];
    //     this.value = value['value'];
    //     if (value['field']['type']['name'] === 'enum') {
    //         this.enumValues.push(Object.keys(value['field']['type']['values']));
    //     }

    // }
}

export class Genres {
    formFields: [FormFields]
}

export class LoginDTO {
    usernameOrEmail: String
    password: String;
}
