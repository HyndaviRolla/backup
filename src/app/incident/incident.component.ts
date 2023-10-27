 // incident-form.component.ts
import { Component } from '@angular/core';
import { IncidentService } from '../incident.service'; 
import { NetworkElement } from '../network-element';
import { Components } from '../component';
import { Incident } from '../incident';
import { User } from '../user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-incident',
  templateUrl: './incident.component.html',
  styleUrls: ['./incident.component.css'],
})
export class IncidentComponent {
  
  search!: string;
  selectedNetworkElement!: NetworkElement;
  selectedComponent!: Components;
  description!: string; 
  selectedSeverity!: string;
  selectedPriority!: string;
  selectedIssueType!:string;
  networkElements!: NetworkElement[];
  components!: Components[];
  myList:Incident[]=[]

  constructor(private incidentService: IncidentService,private authService: AuthService) {}

  searchNetworkElements() {
    this.incidentService.getNetworkElements(this.search).subscribe(
      (networkElements) => {
        this.networkElements = networkElements;
      },
      (error) => {
        console.error('Error fetching network elements', error);
      }
    );

  } 
  ngOnInit(): void { 
      this.loadIncidents();
    } 
    loadIncidents() {
      const currentUser: string | null = this.authService.getCurrentUser();
      const role:string | null = this.authService.getCurrentUserRole();
      console.log(role);
      console.log(currentUser);
      this.incidentService.getIncidentsByUserName(currentUser!)
        .subscribe((data: any[]) => {
          this.myList = data;
        });
    }
  onNetworkElementSelected() {
    if (this.selectedNetworkElement && this.selectedNetworkElement.id ) {
      this.incidentService.getComponents(this.selectedNetworkElement.id).subscribe(
        (components) => {
          this.components = components;
        },
        (error) => {
          console.error('Error fetching components', error);
        }
      );
    }
  }
  submitIncident() {
    const currentUser: string | null = this.authService.getCurrentUser();
    if (!currentUser) {
      console.error('No user logged in.');
      return;
    }
    const currentUserEmail: string | null =this.authService.getCurrentUserEmail();
   
    if (!currentUserEmail) {
      console.error('No user logged in.');
      return;
    }
    console.log(currentUserEmail);
    const incident: Incident = {

      id: null ,
      networkElement: this.selectedNetworkElement,
      component: this.selectedComponent,
      description: this.description,
      issueType: this.selectedIssueType,
      assignmentGroup:" ",
      forwardingmessage:  " ",
      forwardedTo: " ",
      forwarded: false,
      status: " ",
      severity: this.selectedSeverity,
      priority : this.selectedPriority,
      user: currentUser,
      userEmailId:currentUserEmail,
      createdTime:  " ",
      resolution:" ",
      assignedTeamEmail:" ",
      assignedTo:" ",
     
    };

    this.incidentService.createIncident(incident).subscribe(
      (createdIncident) => {
        console.log('Incident created successfully', createdIncident);
        console.log('hii',createdIncident.createdTime);
        
      } 
    );
  }
}
