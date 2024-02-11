import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { APIList } from 'src/app/constants/APIList';
import { generalConstant } from 'src/app/constants/generalConstant';
import { HttpHandlerService } from 'src/app/service/httpHandler/http-handler.service';

@Component({
  selector: 'app-api-details',
  templateUrl: './api-details.component.html',
  styleUrls: ['./api-details.component.css'],
  providers: [generalConstant],
})
export class ApiDetailsComponent implements OnInit {
  mode = new BehaviorSubject<string>('dashboard');
  showMode!: string;
  addAPIForm!: FormGroup;
  methodArray!: string[];
  apiListData!: any[];
  buttonName: string = 'Add';
  apiDataforUpdate!: any;
  buttonNameArray: string[] = ['Dashboard', 'Add API Details'];
  loadingFlag: boolean = false;
  constructor(
    private fb: FormBuilder,
    private generalConstant: generalConstant,
    private httpHandler: HttpHandlerService,
    private apiList: APIList
  ) {}

  ngOnInit(): void {
    this.mode.next('dashboard');
    this.findAllAPI();
    this.methodArray = this.generalConstant.METHOD_ARRAY;
    // console.log('1 mode ' + this.mode);

    this.addAPIForm = this.fb.group({
      apiPath: [''],
      description: [''],
      method: ['Get'],
    });

    this.mode.subscribe({
      next: (res) => {
        console.log(' current mode ' + res);

        if (res == 'add') {
          this.showMode = 'add';
          this.buttonName = this.buttonNameArray[0];
          this.addAPIForm = this.fb.group({
            apiPath: [''],
            description: [''],
            method: ['Get'],
          });
        } else if (res == 'dashboard') {
          this.showMode = 'dashboard';
          this.buttonName = this.buttonNameArray[1];
        } else if (res == 'update') {
          this.showMode = 'update';
          this.buttonName = this.buttonNameArray[0];
        }
      },
    });
  }

  buttonClicked() {
    if (this.showMode == 'dashboard') {
      this.mode.next('add');
    } else {
      this.mode.next('dashboard');
    }
  }

  findTableRowColor(method: string): string {
    let res;

    if (method == 'Get') {
      res = 'table-success';
    } else if (method == 'Put') {
      res = 'table-warning';
    } else if (method == 'Post') {
      res = 'table-primary';
    } else {
      res = 'table-danger';
    }
    return res;
  }

  update(data: any) {
    this.mode.next('update');
    this.apiDataforUpdate = data;
    // console.log(' apiDataforUpdate ' + JSON.stringify(this.apiDataforUpdate));
    this.addAPIForm.controls['apiPath'].setValue(data.apiPath);
    this.addAPIForm.controls['method'].setValue(data.method);
    this.addAPIForm.controls['description'].setValue(data.description);
  }

  formSubmit() {
    // console.log(' API data login ' + JSON.stringify(this.addAPIForm.value));
    this.loadingFlag = true;

    let apiPath: string = this.addAPIForm.get('apiPath')?.value;
    if (!apiPath.startsWith('/')) {
      apiPath = '/' + apiPath;
    }
    if (this.showMode == 'add') {
      const data = {
        method: this.addAPIForm.get('method')?.value,
        description: this.addAPIForm.get('description')?.value,
        apiPath: apiPath,
        host: this.generalConstant.HOST_URL,
      };

      this.httpHandler
        .postMethod(this.apiList.API_DETAILS_SAVE, data)
        .subscribe({
          next: (res) => {
            this.mode.next('dashboard');
            this.findAllAPI();
          },
          error: (err) => console.log(' error in api compoent ' + err),
        });
    } else if (this.showMode == 'update') {
      const data = {
        id: this.apiDataforUpdate.id,
        method: this.addAPIForm.get('method')?.value,
        description: this.addAPIForm.get('description')?.value,
        apiPath: apiPath,
        host: this.generalConstant.HOST_URL,
      };

      this.httpHandler
        .putMethod(this.apiList.API_DETAILS_UPDATE, data)
        .subscribe({
          next: (res) => {
            this.mode.next('dashboard');
            this.findAllAPI();
          },
          error: (err) => console.log(' error in api compoent ' + err),
        });
    }
  }

  findAllAPI() {
    this.loadingFlag = true;
    this.httpHandler.getMethod(this.apiList.API_DETAILS_FIND_AL).subscribe({
      next: (res) => {
        this.apiListData = res;
        this.loadingFlag = false;
      },
      error: (err) => console.log('error in api compoent ' + err),
    });
  }

  delete(id: number) {
    // console.log('deleted ' + id);
    this.loadingFlag = true;
    this.httpHandler
      .deleteMethod(
        this.apiList.API_DETAILS_DELETE_BY_ID.replace('{id}', id.toString())
      )
      .subscribe({
        next: (res) => this.findAllAPI(),
        error: (err) => console.log('error in api details ' + err),
      });
  }
}
