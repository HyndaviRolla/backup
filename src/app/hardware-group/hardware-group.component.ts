import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident';
import { ForwardingRequest } from '../forwadingrequest';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-hardware-group',
  templateUrl: './hardware-group.component.html',
  styleUrls: ['./hardware-group.component.css']
})

export class HardwareGroupComponent {

  showIncidentList: boolean = false;
  showForwardedList: boolean = false;
  showHighPriorityList: boolean = false;
  showOtherList: boolean = false;

  toggleIncidentList() {
    this.showIncidentList = !this.showIncidentList;
  }

  toggleForwardedList() {
    this.showForwardedList = !this.showForwardedList;
  }
  buttonClicked = true;

  toggleHighPriorityList() {
    this.showHighPriorityList = !this.showHighPriorityList;
    this.buttonClicked = !this.buttonClicked;

  }

  toggleOtherList() {
    this.showOtherList = !this.showOtherList;
  }
  
   private currentUser: string | null;
   private currentUserEmail: string | null;
  incidents: Incident[] = [];
  forwardedIncidents: Incident[] = [];
  availableGroups: string[] = [];
  selectedGroup: string | null = null;
  forwardingMessage: string = '';
  highPriorityHighSeverityIncidents: Incident[] = [];

  constructor(
    private route: ActivatedRoute, private incidentService: IncidentService, private authService: AuthService) { 
      
    this.currentUser = this.authService.getCurrentUser();
    this.currentUserEmail = this.authService.getCurrentUserEmail();
    }

  incidentCount: number = 0;
  forwardedIncidentCount: number = 0;
  highPriorityCount: number = 0;
  incidentId!: number;
  idParam!: number;
  ngOnInit() {
    this.fetchIncidents('HardwareGroup');
    this.fetchForwardedIncidents('HardwareGroup');
    this.loadAvailableGroups();

  }
  submitResolution(incident: Incident) {
    if (incident.id !== null) {
      this.incidentService.updateIncidentResolution(incident.id, incident.resolution).subscribe(
        () => {
          console.log('Resolution submitted successfully');

          this.fetchIncidents('HardwareGroup');
        },
        error => {
          console.error('Error submitting resolution', error);
        }
      );
    }
  }

  forwardIncident(incident: Incident) {

    if (!this.selectedGroup) {
      console.error('Please select a group to forward the incident.');
      return;
    }

    if (!this.forwardingMessage) {
      console.error('Please enter a forwarding message.');
      return;
    }

    const forwardingRequest: ForwardingRequest = {
      targetGroup: this.selectedGroup,
      message: this.forwardingMessage,
    };
    this.incidentService.forwardIncident(incident.id!, forwardingRequest).subscribe(
      (forwardedIncidents) => {
        console.log(
          `Incident forwarded successfully to ${this.selectedGroup} group`
        );
        this.fetchIncidents('HardwareGroup');
      },
    );
  }

  fetchForwardedIncidents(group: string) {

    this.incidentService.getForwardedIncidents(group).subscribe(
      (forwardedIncidents) => {
        this.forwardedIncidents = forwardedIncidents;
        this.forwardedIncidentCount = forwardedIncidents.length;

      },
    );
  }


  fetchIncidents(group: string) {
    this.incidentService.getIncidentsByAssignmentGroup(group).subscribe(
      (incidents) => {
        this.incidents = incidents;
        this.incidentCount = incidents.length;

      },
    );
  }

  private loadAvailableGroups() {
    this.availableGroups = ['SoftwareGroup', 'Connectivity Issues', 'Security Operations Center'];
  }

  showDetails(ticketId: number | null) {
    if (ticketId !== null) {
      const sideTab = document.getElementById(`sideTab${ticketId}`);
      if (sideTab) {
        const isVisible = sideTab.style.right === '0px' || !sideTab.style.right;
        sideTab.style.right = isVisible ? '-400px' : '0px';
      } else {
        console.error(`Side tab with ID 'sideTab${ticketId}' not found`);
      }
    } else {
      console.error('Ticket ID is null');
    }
  }

  assignToSelf(incidentId: number | null) {
    const currentUser: string | null = this.authService.getCurrentUser();
    if (!currentUser) {
      console.error('No user logged in.');
      return;
    }

    const currentUserEmail: string | null = this.authService.getCurrentUserEmail();

    if (!currentUserEmail) {
      console.error('No user logged in.');
      return;
    }
    if (incidentId !== null) {
      this.incidentService.assignIncidentToSelf(incidentId,currentUser,currentUserEmail)
        .subscribe(
          updatedIncident => {
            console.log('Incident assigned successfully', updatedIncident);
          },
        );
    } else {
      console.error('Error: incidentId is null');
    }
  }


}