import numberOne from "@/assets/images/welcome/number_one.png";
import numberTwo from "@/assets/images/welcome/number_two.png";
import numberThree from "@/assets/images/welcome/number_three.png";
import numberFour from "@/assets/images/welcome/number_four.png";

export const projectInfo = [
    {
        picture: numberOne,
        title: "交流学习",
        desc: "成功不是将来才有的，而是从决定去做的那一刻起，持续累积而成的。"
    },
    {
        picture: numberTwo,
        title: "平台共建",
        desc: "人生就像一场马拉松比赛，不是谁跑的最快，而是谁能坚持到最后。"
    },
    {
        picture: numberThree,
        title: "优质资讯",
        desc: "如果你想要拥有你从未拥有过的东西，那么你就必须做你从未做过的事情。"
    },
    {
        picture: numberFour,
        title: "共同提升",
        desc: "人生最大的挑战不是你遇到了什么样的困难，而是你如何面对困难。"
    },
]


export const projectList = [
    {
        title: "后台接口服务 所使用技术",
        children: [
            {
                title: "SpringBoot框架",
                desc: "Spring Boot是一个基于Spring框架的快速开发脚手架，" +
                    "它可以帮助开发者快速构建Spring应用程序。Spring Boot" +
                    "提供了自动配置、快速开发、无需XML配置等特性，使得开发者" +
                    "可以更加专注于业务逻辑的开发，而不需要关注底层框架的配置" +
                    "。同时，Spring Boot还提供了丰富的插件和工具，使得开发" +
                    "者可以更加方便地进行应用程序的构建和部署。Spring Boot" +
                    "可以用于构建Web应用程序、RESTful服务、批处理应用程序" +
                    "等各种类型的应用程序。",
            },
            {
                title: "SpringSecurity认证授权框架",
                desc: "Spring Security是一个基于Spring框架的安全框架，" +
                    "提供了一系列的安全控制功能，如认证、授权、密码管理、会" +
                    "话管理、防止攻击等。它可以与各种Web框架（如Spring MVC" +
                    "、Struts、JSF等）集成使用，也可以与各种认证和授权技术" +
                    "（如LDAP、OAuth、OpenID等）集成使用。Spring Security" +
                    "的核心是过滤器链，通过配置不同的过滤器来实现不同的安全控" +
                    "制功能。Spring Security还提供了许多扩展点和插件，使" +
                    "得开发者可以根据自己的需求进行定制和扩展。它是开发Web应" +
                    "用程序时必不可少的安全框架之一。",
            },
            {
                title: "Redis非关系型数据库",
                desc: "Redis是一个开源的非关系型数据库，采用键值对存储数据，" +
                    "支持多种数据结构，如字符串、列表、哈希表、集合等。Redis" +
                    "的特点是高性能、高可用、可扩展、支持事务、支持发布/订阅等" +
                    "，适用于缓存、计数器、排行榜、消息队列、分布式锁等场景。" +
                    "Redis采用内存存储数据，因此读写速度非常快，同时支持数据持" +
                    "久化和主从复制等功能，保证数据的可靠性和可用性。Redis还支" +
                    "持Lua脚本，可以在服务端执行脚本，提高数据处理的效率。Redis" +
                    "广泛应用于Web应用程序、分布式系统、游戏、移动应用等领域。",
            },
            {
                title: "Mysql数据库",
                desc: "MySQL是一个开源的关系型数据库管理系统，是目前最流行" +
                    "、最常用的数据库之一。MySQL支持多种数据存储引擎（如InnoDB" +
                    "、MyISAM等），支持多种操作系统（如Linux、Windows、MacOS" +
                    "等），支持多种编程语言（如Java、PHP、Python等），具有高性" +
                    "能、高可靠性、易扩展等特点。MySQL支持标准的SQL语言以及自定义" +
                    "的存储过程、触发器等，支持ACID事务，保证数据的一致性和可靠性。" +
                    "MySQL还提供了多种工具和插件，如命令行工具、图形界面工具、备份" +
                    "工具、性能分析工具等，方便开发者进行数据库管理和维护。MySQL广" +
                    "泛应用于Web应用程序、企业应用、大数据等领域。",
            }
        ]
    },
    {
        title: "后台管理系统 所使用技术",
        children: [
            {
                title: "Vue2框架",
                desc: "Vue2是一个流行的开源JavaScript框架，用于构" +
                    "建用户界面。Vue2采用MVVM模式，具有轻量、高效、" +
                    "易学等特点，可以与其他库和框架（如React、Angula" +
                    "r等）集成使用。Vue2的核心是数据双向绑定和组件化，" +
                    "通过指令和组件实现数据的动态绑定和交互。Vue2还提" +
                    "供了丰富的插件和工具，如路由、状态管理、服务器端渲" +
                    "染、构建工具等，方便开发者进行各种类型的应用程序开" +
                    "发。Vue2还支持虚拟DOM、异步组件、过渡动画等功能，" +
                    "提高了应用程序的性能和用户体验。Vue2广泛应用于We" +
                    "b应用程序开发、移动应用开发、单页面应用程序等领域。",
            },
            {
                title: "AntdVue UI框架",
                desc: "AntdVue是一个基于Vue2框架的UI库，使用Ant Design" +
                    "的设计语言和风格，提供了一系列的高质量UI组件，如按" +
                    "钮、表单、表格、菜单、弹窗等。AntdVue具有易用、美" +
                    "观、易扩展等特点，支持国际化和响应式设计，适用于" +
                    "各种类型的Web应用程序开发。AntdVue提供了丰富的文" +
                    "档和示例，使得开发者可以快速了解和使用组件，同时还" +
                    "提供了自定义主题和样式的能力，使得开发者可以根据自" +
                    "己的需求进行定制和扩展。AntdVue还支持按需加载和按需" +
                    "打包，减小了应用程序的体积和加载时间。AntdVue是一" +
                    "个非常优秀的UI库，受到了广泛的欢迎和使用。",
            },
            {
                title: "axios网络请求",
                desc: "Axios是一个基于Promise的HTTP客户端，用于浏" +
                    "览器和Node.js平台上发送HTTP请求。Axios支持多种" +
                    "请求方法，如GET、POST、PUT、DELETE等，支持请求" +
                    "和响应拦截器、请求取消、错误处理等功能。Axios使用" +
                    "简单，支持链式调用和配置全局默认值，可以自定义请求" +
                    "头、请求参数、响应数据等。Axios还支持浏览器端和服" +
                    "务器端的CSRF防御和跨域请求，使得开发者可以更加方便" +
                    "地进行Web应用程序开发。Axios是一个非常流行的HTTP" +
                    "客户端库，在前端开发中得到了广泛的应用。",
            }
        ]
    }
]
