import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) { }

  /* тестовый метод, чисто для того, чтобы соединить js со spring и проверить работоспособность */
  getAllUsers(): Observable<any> {
    return this.http.get('//localhost:8080/api/user');
  }

}
