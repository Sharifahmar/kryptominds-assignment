import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseModel } from '../helper/model/api-response-model';
import { HttpClient } from '@angular/common/http';
import { ProfileModel } from '../helper/model/profile-model';



@Injectable({
  providedIn: 'root'
})
export class ProfileService {



  private isEmailExists: string = 'http://localhost:8081/donar/checkEmailAvailability';
  private isPhoneNumberExists: string = 'http://localhost:8081/donar/checkPhoneNumberAvailability';
  private profileApi: string = 'http://localhost:8081/profileRepo';
  private updateDonarApi: string = 'http://localhost:8081/donar/update';
  private getAllDonarsApi: string = 'http://localhost:8081/profileRepo/search/donarList?value=true';
  private getDonarsByIdAndStatusApi: string = 'http://localhost:8081/donarsRepo/search/donarListByIdAndStatus?value=true&id=';
  private deleteDonarApi: string = 'http://localhost:8081/donar/delete';
  private donarSearchCriteriaApi: string ='http://localhost:8081/donar/donarSearchCriteria';

  constructor(private http: HttpClient) { }

  getEmailExist(payload: any): Observable<ApiResponseModel> {
    return this.http.post<ApiResponseModel>(this.isEmailExists, payload);
  }

  getPhoneNumberExist(payload: any): Observable<ApiResponseModel> {
    return this.http.post<ApiResponseModel>(this.isPhoneNumberExists, payload);
  }

  registerProfile(payload: ProfileModel): Observable<ApiResponseModel> {
    return this.http.post<ApiResponseModel>(this.profileApi, payload);
  }

  getAllDonars(): Observable<any> {
    return this.http.get<any>(this.getAllDonarsApi);
  }

  getDonarsByIdAndStatus(data: number): Observable<any> {
    return this.http.get<any>(this.getDonarsByIdAndStatusApi + data);
  }

  deleteDonar(payload: ProfileModel): Observable<ApiResponseModel> {
    return this.http.post<ApiResponseModel>(this.deleteDonarApi, payload);
  }

  updateDonar(payload: ProfileModel): Observable<ApiResponseModel> {
    return this.http.post<ApiResponseModel>(this.updateDonarApi, payload);
  }

  donarSearchCriteria(payload: any): Observable<any> {
    return this.http.post<any>(this.donarSearchCriteriaApi, payload);
  }


}
