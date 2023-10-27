 
import { Components } from "./component";
 

export interface NetworkElement {
  id: number;
  name: string; 
  type: string;
  ipAddress: string;
  model:string;  
  manufacturer: string;
  components: Components[];
}