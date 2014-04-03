ALTER TABLE `ccdb`.`users` 
ADD COLUMN `gcmRegistrationId` VARCHAR(512) NULL DEFAULT 'null' AFTER `isAi`;
