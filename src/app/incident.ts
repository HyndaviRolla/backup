import { Components } from "./component";
import { NetworkElement } from "./network-element";
import { User } from "./user";

export interface Incident {
    id: number | null;
    networkElement: NetworkElement;
    component: Components;
    description: string;
    issueType: string;  
    assignmentGroup: string  | " ";
    forwardingmessage: string | " ";
    forwardedTo: string |" ";
    forwarded: boolean;
    status: string;
    severity: string;
    priority: string;
    user:string;
    createdTime: string;
    resolution:string;
    userEmailId:string;
    assignedTo:string;
    assignedTeamEmail:string;
    
  }
   