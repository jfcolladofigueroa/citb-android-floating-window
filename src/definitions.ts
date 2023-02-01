export interface FloatingWindowPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  minimize(): Promise<void>;
  sendMessage(): Promise<{message: string}>;
}
