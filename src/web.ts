import { WebPlugin } from '@capacitor/core';

import type { FloatingWindowPlugin } from './definitions';

export class FloatingWindowWeb
  extends WebPlugin
  implements FloatingWindowPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    return options;
  }

  async minimize(): Promise<void> {
    throw('Method not implemented');
  }

  async sendMessage(): Promise<{ message: string }> {
    return new Promise<{ message: string }>((resolve)=>{
      resolve({message:"OK"});
    })
  }
  
}
