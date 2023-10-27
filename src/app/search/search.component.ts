import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident';
import { ForwardingRequest } from '../forwadingrequest';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  
 incidents:Incident[]=[];
 
 availableGroups: string[] = [];
 forwardingMessage: string = '';
 selectedGroup: string | null = null;
  searchIdUser: { id?: number; userName?: string } = {}; 
  

  constructor(private incidentService: IncidentService) {}
  search(id: number|undefined, userName: string|undefined) {
    this.incidentService.searchIncidents(id, userName).subscribe(
      (incidents) => {
        this.incidents = incidents; // Update incidents property with the fetched array
      },
      (error) => {
        console.error('Error searching incidents:', error);
        this.incidents = []; // Clear incidents property in case of an error
      }
    );
  }
   
  submitResolution(incident: Incident) {
    if (incident.id !== null) {
      this.incidentService.updateIncidentResolution(incident.id, incident.resolution).subscribe(
        () => {
          console.log('Resolution submitted successfully');
 
        },
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
        //this.fetchIncidents('HardwareGroup');
      },
    );
  }
  private loadAvailableGroups() {
    this.availableGroups = ['SoftwareGroup', 'Connectivity Issues', 'Security Operations Center'];
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
}
