#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd -P)"
cd "$repo_root"

if [[ -n "$(git status --porcelain)" ]]; then
    printf 'Refusing to update: %s has local changes. Commit or stash them first.\n' "$repo_root" >&2
    exit 1
fi

branch="$(git branch --show-current)"
if [[ -z "$branch" ]]; then
    printf 'Refusing to update from a detached HEAD.\n' >&2
    exit 1
fi

if ! upstream="$(git rev-parse --abbrev-ref --symbolic-full-name '@{upstream}' 2>/dev/null)"; then
    printf 'No upstream is configured for branch %s.\n' "$branch" >&2
    exit 1
fi

git fetch --prune
if ! git merge-base --is-ancestor HEAD "$upstream"; then
    printf 'Refusing a non-fast-forward update from %s.\n' "$upstream" >&2
    exit 1
fi
git merge --ff-only "$upstream"

if [[ "$#" -gt 0 ]]; then
    exec "$@"
fi

printf 'Workspace is up to date. Start MCreator now.\n'
