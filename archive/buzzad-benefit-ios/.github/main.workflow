workflow "Publish" {
  on = "release"
  resolves = ["Notify"]
}

action "Filters publish action" {
  uses = "actions/bin/filter@3c0b4f0e63ea54ea5df2914b4fabf383368cd0da"
  args = "action published"
}

action "Publish Cocoapods" {
  uses = "./.github/actions/publish/"
  needs = ["Filters publish action"]
  secrets = ["COCOAPODS_TRUNK_TOKEN"]
}

action "Notify" {
  uses = "Ilshidur/action-slack@f37693b4e0589604815219454efd5cb9b404fb85"
  needs = ["Publish Cocoapods"]
  secrets = ["SLACK_WEBHOOK"]
}
