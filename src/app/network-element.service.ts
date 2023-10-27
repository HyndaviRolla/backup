 
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NetworkElement  } from './network-element';

import { Components  } from './component';

@Injectable({
  providedIn: 'root',
})
export class NetworkElementService {
  private apiUrl = 'http://localhost:8080/api/network-elements';

  constructor(private httpClient: HttpClient) {}

  getAllNetworkElements(): Observable<NetworkElement[]> {
    return this.httpClient.get<NetworkElement[]>(this.apiUrl);
  }

  addNetworkElement(networkElement: NetworkElement): Observable<NetworkElement> {
    return this.httpClient.post<NetworkElement>(this.apiUrl, networkElement);
  }

  addComponentToNetworkElement(id: number, component: Components): Observable<NetworkElement> {
    const url = `${this.apiUrl}/${id}/add-component`;
    return this.httpClient.post<NetworkElement>(url, component);
  } 
  getNetworkElementByName(name: string): Observable<NetworkElement> {
    const url = `${this.apiUrl}/${name}`;
    return this.httpClient.get<NetworkElement>(url);
  }
}
