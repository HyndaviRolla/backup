
import { Component, OnInit } from '@angular/core';
import { NetworkElementService } from '../network-element.service';
import { NetworkElement  } from '/home/hyndavi24/NetworkInventory/src/app/network-element'
import { Components } from '../component';
 

@Component({
  selector: 'app-network-element',
  templateUrl: './network-element.component.html',
  styleUrls: ['./network-element.component.css'],
})
export class NetworkElementComponent implements OnInit { 
  networkElements: NetworkElement[] = [];
  newNetworkElement: NetworkElement = { id: 0, name: '',type:'',model:'',ipAddress:'',manufacturer:'', components: [] };
  newComponent: Components = { componentName: '' };
 
  constructor(private networkElementService: NetworkElementService) {}

  ngOnInit(): void {
    this.loadNetworkElements();
  }

  loadNetworkElements(): void {
    this.networkElementService.getAllNetworkElements().subscribe(
      (data) => (this.networkElements = data),
      (error) => console.error(error)
    );
  }

  addNetworkElement(): void {
    this.networkElementService.addNetworkElement(this.newNetworkElement).subscribe(
      (data) => {
        this.networkElements.push(data);
        this.newNetworkElement = { id: 0, name: '', type:'',model:'',ipAddress:'',manufacturer:'',components: [] };
      //  this.newNetworkElement = {};  
      },
      (error) => console.error(error)
    );
  }

  addComponent(id: number | undefined): void {
    if (id !== undefined) {
      this.networkElementService.addComponentToNetworkElement(id, this.newComponent).subscribe(
        (data) => {
          const index = this.networkElements.findIndex((element) => element.id === id);
          this.networkElements[index] = data;
          this.newComponent = {};  
        },
        (error) => console.error(error)
      );
    }
  }
}
