# [ViewMode](https://developer.android.google.cn/topic/libraries/architecture/viewmodel)

ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据。ViewModel 类让数据可在发生屏幕旋转等配置更改后继续存在。

架构组件为界面控制器提供了 ViewModel 辅助程序类，该类负责为界面准备数据。 在配置更改期间会自动保留 ViewModel 对象