import { Component, OnInit, Input } from '@angular/core';
import { Global } from 'src/app/global/global';
import {  HttpClient } from '@angular/common/http';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';

@Component({
  selector: 'app-file-form',
  templateUrl: './file-form.component.html',
  styleUrls: ['./file-form.component.scss']
})
export class FileFormComponent implements OnInit {

  @Input() formFields: any;

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  isSpiner: Boolean;
  countFile = [];
  maxFile: Boolean;
  minFile: Boolean;
  max: any;
  constructor(
    private global: Global, 
    private httpClient: HttpClient,
    private registrationWriterService: RegistrationWriterService) {
    this.minFile = false;
    this.maxFile = false;
    this.isSpiner = false;
  }

  ngOnInit(): void {
    console.log(this.formFields)
    this.max = this.formFields[0].id.split('_')[2];
    // this.constant = this.formFields[0];
    this.countFile.push({
      id: '',
      link: ''
    })
  }

  ngAddForm() {
    // this.minFile = false;
    // this.maxFile = false;
    // if (this.formFields.length === 0) {
    //   this.formFields.push(this.constant);
      
    // } else if (parseInt(this.formFields.length) < parseInt(this.max)) {
    //   this.formFields.push(this.formFields[0]);

    // } else {
    //   this.maxFile = true;
    // }
  }

  // ngSave() {
    // if (parseInt(this.formFields.length) < parseInt(this.formFields[0].properties.min)) {
    //   this.minFile = true;
    // } else {
      // var datetimestamp = Date.now();
      // let name = '' + datetimestamp;
      // this.fd.append('file', this.selectedFilesHeaderImage, name.toString());

      // console.log(this.fd)
    // }
  // }

  ngRemove(item: any) {
    // this.maxFile = false;
    // this.formFields.splice(this.formFields.indexOf(item), 1);
  }

  onSelectImgForHeader(event: any) {
    // console.log(event.target.files)
    // if (event.target.files && event.target.files[0]) {
    //   console.log('1')
    //   let reader = new FileReader();
    //   console.log('2')
    //   reader.readAsDataURL(event.target.files[0]); // read file as data url
    //   console.log('3')
    //   this.selectedFilesHeaderImage = <File>event.target.files[0];
    //   console.log('5: ' + <File>event.target.files[0])
    //   reader.onload = (event) => { // called once readAsDataURL is completed
    //     console.log('4')
    //     this.urls = event.target['result'];
    //   }
    // }
  }

  public onFileChanged(event) {
    //Select File
    this.isSpiner = true;
    this.selectedFile = event.target.files[0];
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.registrationWriterService.uploadFile(uploadImageData)
      .subscribe(res => {
        // TODO Ovde se implementira spiner
        this.isSpiner = false;
        this._addFile(res);
      })
  }

  _addFile(res: any) {
    if (parseInt(this.formFields.length) < parseInt(this.max)) {
      this.countFile.push({
        id: '',
        link: ''
      })
    } else {
      this.maxFile = true;
    }
  }

  ngSave() {
    if (parseInt(this.formFields.length) < parseInt(this.formFields[0].properties.min)) {
      this.minFile = true;
    } else {
      this.registrationWriterService.saveUpload()
        .subscribe(res => {
          
        })
    }
  }
}
