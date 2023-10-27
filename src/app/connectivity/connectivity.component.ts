 
import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident';
import { ForwardingRequest } from '../forwadingrequest';

@Component({
  selector: 'app-connectivity',
  templateUrl: './connectivity.component.html',
  styleUrls: ['./connectivity.component.css']
})

export class ConnectivityComponent {
  
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
  

  incidents: Incident[] = [];
  forwardedIncidents: Incident[] = [];
  availableGroups: string[] = [];
  selectedGroup: string | null = null;
  forwardingMessage: string = '';
  highPriorityHighSeverityIncidents: Incident[] = [];
  

  constructor(private incidentService: IncidentService) { }

  incidentCount: number = 0;
  forwardedIncidentCount: number = 0;
  highPriorityCount: number = 0;

  ngOnInit() {
    this.fetchIncidents('Connectivity Issues');
    this.fetchForwardedIncidents('Connectivity Issues');
    this.loadAvailableGroups();
    this.loadIncidents();
  } 
  loadIncidents() {
    this.incidentService.getHighPriorityHighSeverityIncidents().subscribe(
      incidents => {
        this.highPriorityHighSeverityIncidents = incidents;
        this.highPriorityCount = incidents.length;
      },
      error => {
        console.error('Error loading high priority high severity incidents', error);
      }
    );

    
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
        this.fetchIncidents('Connectivity Issues');
      },
      (error) => {
        console.error('Error forwarding incident:', error);
      }
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
  submitResolution(incident: Incident) {
    if (incident.id !== null) {
    this.incidentService.updateIncidentResolution(incident.id, incident.resolution).subscribe(
      () => {
        console.log('Resolution submitted successfully');
        // You can optionally reload the incidents after submitting resolution
        this.fetchIncidents('Connectivity Issues');
      },
      error => {
        console.error('Error submitting resolution', error);
      }
    );
  }
}

  fetchIncidents(group: string) {
    this.incidentService.getIncidentsByAssignmentGroup(group).subscribe(
      (incidents) => {
        this.incidents = incidents;
        this.incidentCount = incidents.length;

      },
      (error) => {
        console.error(`Error fetching incidents for ${group} group`, error);
      }
    );
  }
  showDetails(ticketId: number | null) {
    if (ticketId !== null) {
      // Find the side tab associated with the ticket
      const sideTab = document.getElementById(`sideTab${ticketId}`);
  
      // Check if sideTab is not null before accessing its properties
      if (sideTab) {
        // Toggle the visibility of the side tab
        const isVisible = sideTab.style.right === '0px' || !sideTab.style.right;
        sideTab.style.right = isVisible ? '-400px' : '0px';
      } else {
        console.error(`Side tab with ID 'sideTab${ticketId}' not found`);
      }
    } else {
      console.error('Ticket ID is null');
    }
  }

  private loadAvailableGroups() {
    this.availableGroups = ['SoftwareGroup', 'HardwareGroup', 'Security Operations Center'];
  }
}