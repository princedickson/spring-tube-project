import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class saveVideoDetailsComponent implements OnInit{

saveVideoDetailsForm: FormGroup;
title: FormControl = new FormControl('');
description: FormControl = new FormControl('');
videoStatus: FormControl = new FormControl('PUBLIC');

constructor() {
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description: this.description,
      videoStatus: this.videoStatus,
    });
  }

  ngOnInit(): void {}
}
