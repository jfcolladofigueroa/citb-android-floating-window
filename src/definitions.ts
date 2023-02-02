import { PluginListenerHandle } from "@capacitor/core";
export interface FloatingWindowPlugin {
  minimize(): Promise<void>;
  addListener(
    eventName: 'floatingControlAction',
    listenerFunc: (data: {message: string}) => {},
  ):  Promise<PluginListenerHandle> & PluginListenerHandle;

}
