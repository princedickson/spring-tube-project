import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import {MatChipInputEvent} from "@angular/material/chips";
import { ActivatedRoute } from '@angular/router';
import {VideoService} from "../video.service";
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class saveVideoDetailsComponent {


saveVideoDetailsForm: FormGroup;
title: FormControl = new FormControl('');
description: FormControl = new FormControl('');
videoStatus: FormControl = new FormControl('PUBLIC');

selectable = true;
removable = true;
addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!: File;
  selectedFileName = '';
  videoId = '';
  FileSelected = false;

constructor(private activatedRoute: ActivatedRoute, private videoService: VideoService,
                    private _snackBar: MatSnackBar) {
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description: this.description,
      videoStatus: this.videoStatus,
    });
  }

  ngOnInit(): void {}

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);

    }
  }
  onFileSelected(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target?.files && target.files.length > 0) {
      this.selectedFile = target.files[0];
      this.selectedFileName = this.selectedFile.name;
      this.FileSelected = true;
    }
  }
  onUpload(){
  this.videoService.uploadThumbnail(this.selectedFile, this.videoId)
  .subscribe(data=>{
    console.log(data);

    // show notification after upload
    this._snackBar.open("thumbnail upload successful", "ok");
  })
  }
}
