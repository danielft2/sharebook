module.exports = { 
   extends: ["@commitlint/config-conventional"],
   rules: {
      'scope-enum': [2, 'always', ['cliente', 'server', 'root']],
      'scope-empty': [2, 'never']
   }
};
