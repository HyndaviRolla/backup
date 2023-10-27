import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NetworkElementComponent } from './network-element/network-element.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IncidentComponent } from './incident/incident.component';
import { HardwareGroupComponent } from './hardware-group/hardware-group.component';
import { SoftwareGroupComponent } from './software-group/software-group.component';
import { AuthInterceptor } from './auth.interceptor';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { MyListComponent } from './my-list/my-list.component';
import { ConnectivityComponent } from './connectivity/connectivity.component';
import { StatusComponent } from './status/status.component';
import { SlaComponent } from './sla/sla.component';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    NetworkElementComponent,
    IncidentComponent,
    HardwareGroupComponent,
    SoftwareGroupComponent,
    NavBarComponent,
    LoginComponent,
    MyListComponent,
    ConnectivityComponent,
    StatusComponent,
    SlaComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,ReactiveFormsModule  ],
  providers: [  

    {

      provide: HTTP_INTERCEPTORS,

      useClass: AuthInterceptor,  

      multi: true,

    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
