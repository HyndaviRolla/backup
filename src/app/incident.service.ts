import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Incident } from './incident';
import { Components } from './component';
import { ForwardingRequest } from './forwadingrequest';
import { NetworkElement } from './network-element';
@Injectable({
  providedIn: 'root',
})
export class IncidentService {
  private apiUrl = 'http://localhost:8080/api/incidents';

  constructor(private http: HttpClient) {}

  createIncident(incident: Incident): Observable<Incident> {
    return this.http.post<Incident>(`${this.apiUrl}`, incident);
  }

  getNetworkElements(search: string): Observable<NetworkElement[]> {
    return this.http.get<NetworkElement[]>(`${this.apiUrl}/network-elements?search=${search}`);
  }

  getComponents(networkElementId: number): Observable<Components[]> {
    return this.http.get<Components[]>(`${this.apiUrl}/components/${networkElementId}`);
  } 

  getIncidentsByUserName(userName: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/user/${userName}`);
  }

  getIncidentsByAssignmentGroup(assignmentGroup: string): Observable<Incident[]> {
  return this.http.get<Incident[]>(`${this.apiUrl}/assigned-group/${assignmentGroup}`);
   }

forwardIncident(incidentId: number, forwardingRequest: ForwardingRequest): Observable<Incident> {
  const url = `${this.apiUrl}/forward/${incidentId}`;
  return this.http.post<Incident>(url, forwardingRequest);
}
getForwardedIncidents(group: string): Observable<Incident[]> {
  const url = `${this.apiUrl}/forwarded/${group}`;
  return this.http.get<Incident[]>(url);
}
 
getHighPriorityHighSeverityIncidents(): Observable<Incident[]> {
  return this.http.get<Incident[]>(`${this.apiUrl}/high-priority-high-severity`);
}

getOtherIncidents(): Observable<Incident[]> {
  return this.http.get<Incident[]>(`${this.apiUrl}/other-incidents`);
}
updateIncidentResolution(incidentId: number, resolution: string): Observable<void> {
  const url = `${this.apiUrl}/incidents/${incidentId}/resolution`;  
  const body = { resolution };

  return this.http.put<void>(url, body);
}
getIncidentsByStatus(status: string): Observable<Incident[]> {
  const url = `${this.apiUrl}/status/${status}`;
  return this.http.get<Incident[]>(url);
}
 
getIncidentsDueToday(): Observable<Incident[]> { 
  const url = `${this.apiUrl}/due-today`;
   
  return this.http.get<Incident[]>(url);
}
searchIncidents(id?: number, userName?: string): Observable<Incident[]> {
  const params: any = {};
  if (id) {
    params.id = id;
  }
  if (userName) {
    params.userName = userName;
  }

  return this.http.get<Incident[]>(`${this.apiUrl}/search`, { params });
}
assignIncidentToSelf(incidentId: number,currentUser:string,currentUserEmail:string): Observable<Incident> {
  const url = `${this.apiUrl}/${incidentId}/assign-to-self`;
  const body = { currentUser, currentUserEmail };
  return this.http.post<Incident>(url, {});
}
}  
