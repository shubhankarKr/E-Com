import { HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHandlerService } from '../httpHandler/http-handler.service';
import { APIList } from 'src/app/constants/APIList';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  constructor(private httpHandler:HttpHandlerService,private apiList:APIList) { }

  findAllCategoriesMD():Observable<any[]>{
    return this.httpHandler.getMethod(this.apiList.METADATA_FIND_ALL_CATEGORIES_MD);
  }
}
