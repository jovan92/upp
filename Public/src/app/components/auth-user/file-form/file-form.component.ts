import { Component, OnInit, Input } from '@angular/core';
import { Global } from 'src/app/global/global';
import {  HttpClient } from '@angular/common/http';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';
import { FilesService } from 'src/app/services/files.service';

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
  user: any;
  constructor(private filesService: FilesService) {
    this.minFile = false;
    this.maxFile = false;
    this.isSpiner = false;
  }

  ngOnInit(): void {
    console.log(this.formFields)
    this.max = this.formFields[0].id.split('_')[2];
    this.user = JSON.parse(localStorage.getItem('user'))
    // this.constant = this.formFields[0];
    this.countFile = []
    // TODO ovo ispraviti posle da dolazi sve kroz jedan zahtjev
    this._getFiles();
  }

  _getFiles() {
    this.filesService.getAllFilesByUser(this.user)
      .subscribe(res => {
        this.countFile = res;
      })
  }

  ngRemove(item: any, i: any) {
    console.log(item)
    this.filesService.removeFiles(item)
      .subscribe(res => {
        if (res) {
          this.countFile.splice(i, 1);
        }
      })
  }

  public onFileChanged(event) {
    if ((this.countFile.length + 1) < parseInt(this.max)) {
      this.isSpiner = true;
      this.selectedFile = event.target.files[0];
      const uploadImageData = new FormData();
      uploadImageData.append('pdf', this.selectedFile, this.selectedFile.name);
      this.filesService.uploadFile(uploadImageData, this.user)
        .subscribe(res => {
          // TODO Ovde se implementira spiner
          res.forEach(element => {
            this.countFile.push(element);
          });
          this.isSpiner = false;
        })
    } else {
      this.isSpiner = false;
      this.maxFile = true;
    }
  }

  ngSave() {
    console.log(this.countFile.length)
    
    let i = parseInt(this.formFields[0].properties.min);
    console.log(i)

    console.log(this.formFields.length < i)
    if (this.formFields.length < i) {
      this.filesService.commitedFiles()
        .subscribe(res => {
          
        })
    } else {
      this.minFile = true;
    }
  }
}
