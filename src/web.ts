import { WebPlugin } from '@capacitor/core';

import type { FloatingWindowPlugin } from './definitions';

export class FloatingWindowWeb
  extends WebPlugin
  implements FloatingWindowPlugin
{
  async minimize(): Promise<void> {
    throw('Method not implemented');
  }
  
}
