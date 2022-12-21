import Foundation

@objc public class FloatingWindow: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
