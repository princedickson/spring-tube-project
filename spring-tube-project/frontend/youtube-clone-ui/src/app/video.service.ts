import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FileSystemEntry} from "ngx-file-drop";
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) {
    }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse>{
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);

    // http post call to upload video
    return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/videos", formData);
  }
  uploadThumbnail(fileEntry: File, videoId: string): Observable<string>{
      const formData = new FormData()
      formData.append('file', fileEntry, fileEntry.name);
      formData.append('videoId', videoId)

      // http post call to upload thumbnail
      return this.httpClient.post("http://localhost:8080/api/videos/thumbnail", formData,{
      responseType: 'text'
      });
  }
}
