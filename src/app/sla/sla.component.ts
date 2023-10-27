import { Component } from '@angular/core';
import { Incident } from '../incident';
import { IncidentService } from '../incident.service';
import { ForwardingRequest } from '../forwadingrequest';

@Component({
  selector: 'app-sla',
  templateUrl: './sla.component.html',
  styleUrls: ['./sla.component.css']
})
export class SlaComponent {
  incidents: Incident[] = [];
  
  availableGroups: string[] = [];
  forwardingMessage: string = '';
  selectedGroup: string | null = null;
  constructor(private incidentService: IncidentService) {}
  ngOnInit(): void {
    this.fetchIncidents();
  }

  fetchIncidents(): void {
    console.log("hii");
    this.incidentService.getIncidentsDueToday()
      .subscribe(incidents => this.incidents = incidents);
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
