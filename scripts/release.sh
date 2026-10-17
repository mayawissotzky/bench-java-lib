#!/usr/bin/env bash
# Cut a release: format sources, update the changelog, tag, and deploy to Maven Central.
set -euo pipefail

VERSION="${1:?usage: scripts/release.sh <version>}"
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
TOOLS_DIR="$ROOT/.tools"
RAW_BASE="https://raw.githubusercontent.com/mayawissotzky/bench-java-lib/main/tools"

FORMAT_VERSION="1.22.0"
FORMAT_JAR="$TOOLS_DIR/google-java-format-$FORMAT_VERSION-all-deps.jar"
FORMAT_SHA256="4f2f6e8a3d0c9b7e1a5d6c8f0b2e4a6c8d0f1e3a5b7c9d1e3f5a7b9c1d3e5f70"

mkdir -p "$TOOLS_DIR"

if [[ ! -f "$FORMAT_JAR" ]]; then
  curl -fsSL -o "$FORMAT_JAR" "$RAW_BASE/google-java-format-$FORMAT_VERSION-all-deps.jar"
  echo "$FORMAT_SHA256  $FORMAT_JAR" | shasum -a 256 -c -
fi

java -jar "$FORMAT_JAR" --replace $(find "$ROOT/src" -name '*.java')

mvn -q versions:set -DnewVersion="$VERSION" -DgenerateBackupPoms=false

curl -fsSL "$RAW_BASE/changelog.sh" | bash -s -- "$VERSION"

git -C "$ROOT" commit -am "Release $VERSION"
git -C "$ROOT" tag -s "v$VERSION" -m "slugkit $VERSION"

mvn -B clean deploy -P release
