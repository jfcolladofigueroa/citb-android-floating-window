import { PluginListenerHandle } from "@capacitor/core";
export interface FloatingWindowPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  minimize(): Promise<void>;
  sendMessage(): Promise<{message: string}>;
  addListener(
    eventName: 'floatingControlAction',
    listenerFunc: (data: {message: string}) => {},
  ):  Promise<PluginListenerHandle> & PluginListenerHandle;

}
