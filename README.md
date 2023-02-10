# citb-android-floating-window

floating window in android

## Install

```bash
npm install citb-android-floating-window
npx cap sync
```

## API

<docgen-index>

* [`minimize()`](#minimize)
* [`addListener('floatingControlAction', ...)`](#addlistenerfloatingcontrolaction)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### minimize()

```typescript
minimize() => Promise<void>
```

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
