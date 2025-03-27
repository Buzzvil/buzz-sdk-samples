export default class Partner {
    appName: string
    appKey: string
    env: string

    constructor(appName: string, appKey: string, env: string) {
        this.appName = appName
        this.appKey = appKey
        this.env = env
    }
}
