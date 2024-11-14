export class BuzzBoosterUser {
    userId!: string
    optInMarketing!: boolean | null
    properties!: Map<string, string | boolean | number>
    
    constructor(builder: BuzzBoosterUserBuilder){
      this.userId = builder.userId
      this.optInMarketing = builder.optInMarketing
      this.properties = builder.properties
    }
}

export class BuzzBoosterUserBuilder {
    userId!: string
    optInMarketing!: boolean | null
    properties!: Map<string, string | boolean | number >
  
    constructor(userId: string) {
      this.userId = userId
      this.optInMarketing = null
      this.properties = new Map()
    }
    
    setOptInMarketing(optInMarketing: boolean): BuzzBoosterUserBuilder {
      this.optInMarketing = optInMarketing
      return this
    }
  
    addProperty(key: string, value: string | boolean | number): BuzzBoosterUserBuilder {
      this.properties[key] = value
      return this
    }
  
    build(): BuzzBoosterUser {
      return new BuzzBoosterUser(this)
    }
  }
