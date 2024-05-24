from api.character.character_attributes import Playbook
from api.character.character_skills import Skill, Prowess, Resolve, Insight

PLAYBOOK_DEFAULTS = {
    Playbook.CUTTER: {
        Skill.PROWESS: Prowess(skirmish=2),
        Skill.RESOLVE: Resolve(command=1),
    },
    Playbook.HOUND: {
        Skill.INSIGHT: Insight(hunt=2, survey=1),
    },
    Playbook.LEECH: {
        Skill.INSIGHT: Insight(tinker=2),
        Skill.PROWESS: Prowess(wreck=1),
    },
    Playbook.LURK: {
        Skill.PROWESS: Prowess(finesse=1, prowl=2),
    },
    Playbook.SLIDE: {
        Skill.RESOLVE: Resolve(consort=1, sway=2),
    },
    Playbook.SPIDER: {
        Skill.INSIGHT: Insight(study=1),
        Skill.RESOLVE: Resolve(consort=2),
    },
    Playbook.WHISPER: {
        Skill.INSIGHT: Insight(study=1),
        Skill.RESOLVE: Resolve(attune=2),
    },
}
