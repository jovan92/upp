import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class Global {

    constructor() {}

    _parserForms(forms: any) {
        forms.map((field) => {
          if (field.typeName === 'multipleEnum_genres') {
            let test: string = field.type.values.all;
            field.type.values = JSON.parse(test);
          }
          if (field.typeName === 'boolean') {
            field.type['values'] = [];
    
            let test = JSON.stringify(field.properties);
            let test1 = test.split('{')[1];
            let testYES = test1.split('}')[0].split(',')[0].split(':');
            field.type['values'].push({id: JSON.parse(testYES[0]), type: JSON.parse(JSON.parse(testYES[1]))})
            let testNO = test1.split('}')[0].split(',')[1].split(':');
            field.type['values'].push({id: JSON.parse(testNO[0]), type: JSON.parse(JSON.parse(testNO[1]))})
          }
          if (field.typeName === 'string') {
              if (field.id === 'files_2_10') {
                  let count = parseInt(field.properties.min);
                  console.log(count)
                  
                  // for(let i=0; i<count; count++) {
                  //   console.log('dosao ovde')
                  // }
              }
          }
        })

        return forms;
      }
}
