# citb-android-floating-window

floating window in android

## Install

```bash
npm install citb-android-floating-window
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`minimize()`](#minimize)
* [`sendMessage()`](#sendmessage)
* [`addListener('floatingControlAction', ...)`](#addlistenerfloatingcontrolaction)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### minimize()

```typescript
minimize() => Promise<void>
```

--------------------


### sendMessage()

```typescript
sendMessage() => Promise<{ message: string; }>
```

**Returns:** <code>Promise&lt;{ message: string; }&gt;</code>

--------------------


### addListener('floatingControlAction', ...)

```typescript
addListener(eventName: 'floatingControlAction', listenerFunc: (data: { message: string; }) => {}) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                               |
| ------------------ | -------------------------------------------------- |
| **`eventName`**    | <code>'floatingControlAction'</code>               |
| **`listenerFunc`** | <code>(data: { message: string; }) =&gt; {}</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |

</docgen-api>
