workflow "Publish" {
  # resolves = ["docker"]
  resolves = ["Publish Cocoapods"]
  on = "release"
}

# action "Filters publish action" {
#   uses = "actions/bin/filter@3c0b4f0e63ea54ea5df2914b4fabf383368cd0da"
#   args = "action publish"
# }

action "Publish Cocoapods" {
  uses = "./.github/actions/publish/"
  # needs = ["Filters publish action"]
}
