 
import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';

import { catchError, tap } from 'rxjs/operators';
import { User } from './user';

 

@Injectable({

    providedIn: 'root',

})

 

export class AuthService {

    private apiUrl = 'http://localhost:8080/api/auth/token';
    private currentUser: User | null = null;

    constructor(private httpclient: HttpClient) { }

 

    login(username: any, password: any): Observable<any> {
        return this.httpclient.post<any>(`${this.apiUrl}`, { username: username, password: password }).pipe(
          tap(res => {
            console.log('Login Response:', res);
            localStorage.setItem('token', res["token"]);
            localStorage.setItem('role',res["role"]);
            localStorage.setItem('username', res["username"]);
            localStorage.setItem('email',res["email"]);
          })
        );
      }

 
      getCurrentUser(): string | null {
        return localStorage.getItem('username');
      }
      getCurrentUserRole():string | null{
        return localStorage.getItem('role');
      }
      getCurrentUserEmail():string | null{
        return localStorage.getItem('email');
      }
    // login(username: string, password: string){

    //     const body = { username, password };

    //     return this.httpclient.post(`${this.apiUrl}`, body).pipe(



    //         tap(res => {
    //             localStorage.setItem('token', res["token"]);
    //             localStorage.setItem('username', res["username"]);
    //           })
    //         );

        //     tap( (res:any) => {
                
               

        //         localStorage.setItem('token',res['token']);

        //     }),

        //     catchError((error: HttpErrorResponse) => {

        //         console.error('Authentication error:', error);

        //         return throwError(error);

        //     })

        // );

    }

    

    // setToken(token: string): void {

    //     localStorage.setItem('token', token);

    // }

 

    // getToken(): string | null {

    //     return localStorage.getItem('token');

    // }

 

    // getHeaders(): HttpHeaders {

    //     const token = this.getToken();

    //     return new HttpHeaders({

    //         'Content-Type': 'application/json',

    //         Authorization: `Bearer ${token}`,

    //     });

    // } 

