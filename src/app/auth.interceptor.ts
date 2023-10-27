 
import { Injectable } from '@angular/core';

import {

  HttpInterceptor,

  HttpRequest,

  HttpHandler,

  HttpEvent,

} from '@angular/common/http';

import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

 

@Injectable()

export class AuthInterceptor implements HttpInterceptor {
  
  constructor(private authService: AuthService, private router: Router) {}
 

  intercept(

    request: HttpRequest<any>,

    next: HttpHandler

  ): Observable<HttpEvent<any>> { 
    const authToken = localStorage.getItem('token');

 

    if (request.url.endsWith('/api/auth/token')) {

     

      return next.handle(request);

    }
 
    if (authToken) {

      request = request.clone({

        setHeaders: {

          Authorization: `Bearer ${authToken}`,

        },

      });

    }
 

  if (request.url.endsWith('/hardware') && this.authService.getCurrentUserRole()=="ADMIN") {
    
    this.router.navigate(['/login']);
    return next.handle(request); // Return the original request without sending it
  }

  return next.handle(request);
}

}

 